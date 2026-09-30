package com.surveillance;

import com.surveillance.abstraction.ArchiveProcessor;
import com.surveillance.abstraction.MotionDetectionProcessor;
import com.surveillance.abstraction.StreamProcessor;
import com.surveillance.adapter.CctvAdapter;
import com.surveillance.adapter.CctvAnalogReceiver;
import com.surveillance.adapter.CctvStatus;
import com.surveillance.exception.StreamLostException;
import com.surveillance.implementor.VideoSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BridgeAdapterTest {

    @Mock
    private VideoSource mockVideoSource;

    @Mock
    private CctvAnalogReceiver mockAnalogReceiver;

    @Test
    void testMotionDetectionProcessor_NormalDelegation() throws StreamLostException {
        // Настраиваем стаб (stub)
        when(mockVideoSource.fetchFrame()).thenReturn(new byte[]{1, 2, 3});

        StreamProcessor processor = new MotionDetectionProcessor(mockVideoSource);
        processor.process();

        // Проверяем, что абстракция действительно вызвала метод интерфейса (делегация)
        verify(mockVideoSource, times(1)).fetchFrame();
    }

    @Test
    void testArchiveProcessor_NormalDelegation() throws StreamLostException {
        // Настраиваем стаб
        when(mockVideoSource.fetchFrame()).thenReturn(new byte[]{5, 5});

        StreamProcessor processor = new ArchiveProcessor(mockVideoSource);
        processor.process();

        // Проверяем делегацию во второй абстракции
        verify(mockVideoSource, times(1)).fetchFrame();
    }

    @Test
    void testAdapter_FailureTranslation() {
        // Настраиваем несовместимый класс на возврат ошибки (errorCode = -1)
        CctvStatus errorStatus = new CctvStatus();
        errorStatus.errorCode = -1;
        when(mockAnalogReceiver.readRawBuffer(anyInt())).thenReturn(errorStatus);

        // Создаем адаптер
        VideoSource adapter = new CctvAdapter(mockAnalogReceiver, 1024);

        // Проверяем, что адаптер ПЕРЕВОДИТ (translates) статус -1 в исключение StreamLostException
        assertThrows(StreamLostException.class, () -> {
            adapter.fetchFrame();
        }, "Adapter must translate errorCode -1 into StreamLostException");
    }
}