# ATS SDK Sample App

The project is meant to provide developers a functional code example that can be used to debug, step through, and analyze. Additionally, the project aims to demonstrate how LiveRamp expects the SDK methods to be utilized and to provide guidance on which methods are appropriate for specific use cases. 

## Getting Started

1. Clone the repository
    ```bash
    git clone https://github.com/LiveRamp/ats-sdk-android-sample
    ```
2. Open LRAtsSDKSample in AndroidStudio

## Code Structure and Organization

The source code for the sample application is accessible within the `LRAtsSDKSample` subdirectory, which is structured as follows:

* `Examples`
    * This directory contains a collection of code snippets demonstrating various use cases of `LRAtSDK`. Each nested `Fragment` is dedicated to illustrating a specific use case. The `configureLRAtsExample` method in the `Fragment` contains all the code that is related to the use of the SDK.
* `Internal`
    * **Caution**: Do not copy or use this code in production.
    * This directory contains supporting code for certain functionalities presented in the sample application. It is unrelated to the core behavior or the usage of the `LRAtsSDK`.

## Consent handling

In order to use the SDK's features, specific requirements must be fulfilled. One of the most crucial requirements is obtaining consent to use personal data. For the purpose of demonstration in this project, we have simulated the user's consent. However, it is important to note that the code used to simulate consent is not suitable for use in a production environment.

## License
This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details.
