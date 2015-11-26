SAP HANA Cloud Platform Samples - enterprise granny
========

<p align="center">
  <img src="/doc/ensw_granny_logo_web.png" width="25%">
</p>

Enterprise Granny is a sample application used to demonstrate how-to develop great cloud applications with the SAP HANA Cloud Platform. Step by step we will enhance this simple demo app and make it _enterprise-ready_ by using open source software and by leveraging some of the capabilities of the platform.

<p align="center">
[![Introduction video](http://img.youtube.com/vi/t1rZDX-0PQI/0.jpg)](https://www.youtube.com/watch?v=t1rZDX-0PQI)
<p align="center">

**Note:** The initial source code is a fork of the original [Granny's Addressbook](https://github.com/osintegrators/JavaSpringGranny) application provided by Open Software Integrators. 

Quick start
-----------

Clone the repo, `git clone https://github.com/SAP/cloud-enterprise-granny.git`, or [download the latest release](https://github.com/SAP/cloud-enterprise-granny/archive/master.zip).

With the introduction of the 2.x branch (which is now the new master branch) the application has been refactored into three modules:

+ a shared `enterprise-granny-core` module, which contains the domain model and the API,
+ a (micro-) service (provider) called `enterprise-granny-service` and
+ an exemplary client application called `enterprise-granny-client` 

The later two are both web applications (packaged as WAR files by default) and hence, need to be deployed separately. As the client uses the REST API of the service (provider) one needs to specify the endpoint URL pointing to the API. This is done as described below:

+ _SAP HANA Cloud Platform (NEO):_ Define an HTTP destination with the name `Addressbook-Service`. The client app comes with an example, which can be found here: [destination.properties](enterprise-granny-client/src/main/resources/destinations/destination.properties)
+ _Cloud Foundry:_ Define a user-defined environment variable with the name `ADDRESSBOOK_SERVICE` and the value of the URI, as shown below: 

```
ADDRESSBOOK_SERVICE=https://enterprise-granny-service.cfapps.neo.ondemand.com/api/v1
```

Please also remember to adjust the declared services within the [`manifest.yml`](enterprise-granny-client/manifest.yml) file as required for your runtime environment! 

> **NOTE:** If you intend to deploy this application to a Cloud Foundry landscape provided by SAP or its partners in order to leverage the capabilities of the SAP HANA database platform you need to manually provide the HANA JDBC driver (`ngdbc.jar`) within the [`WEB-INF/lib`](enterprise-granny-service/src/main/webapp/WEB-INF/lib) folder. 

Table of Content
-----------

+ [Chapter 01 - There's no spoon - but a fork! ](/doc/01.md)
+ [Chapter 02 - Making it run](/doc/02.md)
+ [Chapter 03 - The Good, the Bad and the Ugly](/doc/03.md)
+ [Chapter 04 - Get in control](/doc/04.md)
+ [Chapter 05 - Granny's Next Topmodel](/doc/05.md)
+ [Chapter 06 - Back to the basics](/doc/06.md)
+ [Chapter 07 - Put Granny to the test](/doc/07.md)
+ [Chapter 08 - Inner beauty](/doc/08.md)
+ [Chapter 09 - The Road to API-ness](/doc/09.md)
+ [Chapter 10 - Everybody's favorite : documentation](/doc/10.md)
+ [Chapter 11 - One for all](/doc/11.md)

+ [Chapter 20 - Granny 2.0: A new Beginning](/doc/20a.md)
+ [Chapter 20 - Granny 2.0: A new Beginning - Part II](/doc/20b.md)

Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, the SAP HANA Cloud Platform -Samples project will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the following format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major (and resets the minor and patch)
* New additions without breaking backward compatibility bumps the minor (and resets the patch)
* Bug fixes and misc changes bumps the patch

For more information on SemVer, please visit http://semver.org/


Authors
-------

**Matthias Steiner**

+ http://twitter.com/steinermatt
+ http://github.com/steinermatt


Copyright and license
---------------------

Copyright (c) 2013-2015 SAP SE

Except as provided below, this software is licensed under the Apache License, Version 2.0 (the "License"); you may not use this software except in compliance with the License.You may obtain a copy of the License at:

[http://www.apache.org/licenses/LICENSE-2.0] (http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

- - -

This software includes the following component that is subject to the following terms: 

JavaSpringGranny - Granny's Addressbook with Java + Spring MVC + PostgreSQL

Avaiable at [https://github.com/osintegrators/JavaSpringGranny] (https://github.com/osintegrators/JavaSpringGranny)

Copyright (c) 2012 Open Software Integrators

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

