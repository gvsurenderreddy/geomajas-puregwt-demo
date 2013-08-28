geomajas-puregwt-demo
=====================

[geomajas-layer-deegree](https://github.com/martin-vi/geomajas-layer-deegree)

example geomajas application using the [geomajas-layer-deegree][] as vector layer and the pure gwt face.

screenshot
----------

[screenshot]: https://github.com/martin-vi/geomajas-puregwt-demo/raw/master/resources/geomajasPureGWTClient.png

![screenshot][screenshot]

installation
------------

* compile [geomajas-layer-deegree][]
```bash
$ git clone https://github.com/martin-vi/geomajas-layer-deegree.git
$ cd geomajas-layer-deegree
$ mvn clean install
```

* clone the geomajas-puregwt-demo application
```bash
% git clone https://github.com/martin-vi/geomajas-puregwt-demo.git
```

* configure _geomajas-layer-deegree_ under **geomajas-puregwt-demo/src/main/webapp/WEB-INF/layerDeegreeWfs.xml**
  * setup wfs client configuration
```xml
    <bean name="layerWfs" class="org.deegree.DeegreeLayer">
      <property name="url" value="http://hostname:PORT/services/wfs" />
      <property name="featureTypeName" value="MY_WFS_FEATURE" />
      <property name="featureGeometyPropertyName" value="geometryProperty" />
      <property name="layerInfo" ref="layerWfsLayerInfo" />
    </bean>
```
  * define attributes for the feature type
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
  * compile the webapp

```bash
$ cd geomajas-puregwt-demo
$ mvn clean install
```
  * ensure wfs server is running (e.g. local [deegree][http://www.deegree.org/Download] wfs serice )
  * run the application either with jetty or tomcat

```bash
$ mvn jetty:run
```

  * navigate your browser to [http://localhost:8080/](http://localhost:8080/)
