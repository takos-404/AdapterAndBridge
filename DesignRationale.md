Design Rationale: Video Surveillance Processing

1. Problem Domain
   The system manages video streams from various surveillance cameras to perform distinct processing tasks (e.g., motion detection, archiving). The domain faces two axes of change: the type of processing logic and the hardware protocol of the cameras (IP, USB, Analog).

2. Why one pattern alone is insufficient

Bridge alone: Would separate processing logic from camera types but would fail to integrate the legacy CctvAnalogReceiver. This legacy system uses error codes instead of exceptions and requires buffer sizes rather than simple frame fetching, making it impossible to implement the standard VideoSource interface directly.

Adapter alone: If we only used Adapter without Bridge, we would need to create a concrete subclass for every combination of processor and camera type (e.g., IpCameraMotionDetector, AnalogArchiveSaver). This leads to combinatorial subclass explosion.

3. Genuine Incompatibility
   The CctvAnalogReceiver is genuinely incompatible because:

It uses a different failure mechanism: returning a CctvStatus object with an errorCode (where -1 means no signal) instead of throwing exceptions.

It requires specific arguments (bufferSize) that are not present in the standard fetchFrame() signature.
The CctvAdapter successfully translates these error codes into standard StreamLostException occurrences, ensuring the abstraction side never deals with legacy error handling.

4. Required Complexity Module
   Dynamic implementor selection: The client code does not hard-code the camera implementation. Instead, VideoSourceFactory parses a configuration URL (e.g., ip:// vs analog://) at runtime to dynamically instantiate the correct VideoSource or wrap the legacy system in a CctvAdapter.

5. Design Limitation
   One limitation is that the CctvAdapter relies on a fixed, pre-configured bufferSize injected via its constructor. If the analog receiver dynamically changes its resolution during a stream, the adapter currently cannot negotiate a new buffer size on the fly.