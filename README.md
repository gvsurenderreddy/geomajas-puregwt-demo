geomajas-puregwt-demo
=====================

example geomajas application using the [geomajas-layer-deegree](https://github.com/martin-vi/geomajas-layer-deegree) as vector layer and the pure gwt face.

screenshot
----------

[screenshot]: https://github.com/martin-vi/geomajas-puregwt-demo/raw/master/resources/geomajasPureGWTClient.png

![screenshot][screenshot]

Installation
------------

* compile [geomajas-layer-deegree](https://github.com/martin-vi/geomajas-layer-deegree)
```bash
$ git clone https://github.com/martin-vi/geomajas-layer-deegree.git
$ cd geomajas-layer-deegree
$ mvn clean install
```

* clone the geomajas-puregwt-demo application
```bash
% git clone https://github.com/martin-vi/geomajas-puregwt-demo.git
```

* configure _geomajas-layer-deegree_ under geomajas-puregwt-demo/src/main/webapp/WEB-INF/layerDeegreeWfs.xml

1. setup wfs client configuration

  ```xml
    <bean name="layerWfs" class="org.deegree.DeegreeLayer">
      <property name="url" value="http://hostname:PORT/services/wfs" />
      <property name="featureTypeName" value="MY_WFS_FEATURE" />
      <property name="featureGeometyPropertyName" value="geometryProperty" />
      <property name="layerInfo" ref="layerWfsLayerInfo" />
    </bean>
  ```
2. define attributes for the feature type

  ```xml
    <bean class="org.geomajas.configuration.FeatureInfo" name="layerWfsLayerFeatureInfo">
      <property name="identifier">
        [...]
      </property>
      <property name="geometryType">
        [...]
      </property>
      <property name="attributes">
        <list>
          <bean class="org.geomajas.configuration.PrimitiveAttributeInfo">
            [...]
          </bean>
          [...]
        </list>
      </property>
    </bean>
   ```
3. compile the webapp

  ```bash
$ cd geomajas-puregwt-demo
$ mvn clean install
  ```
4. ensure wfs server is running (e.g. local [deegree](http://www.deegree.org/Download) wfs serice )
5. run the application either with jetty or tomcat

  ```bash
$ mvn jetty:run
  ```

6. navigate your browser to [http://localhost:8080/](http://localhost:8080/)

Known issues
------------

* the _Click_ button has no use for now, the package containing the FeatureClickedListener in geomajas somehow did not keep up with the latest releases
* not all filter are yet implemented in geomajas-layer-deegree
* in large scale the get feature tool is not very accurate, induced by rounding in deegree (geomajas to deegree geometry conversion)
