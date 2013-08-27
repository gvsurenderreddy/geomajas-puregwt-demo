<?xml version="1.0" encoding="ISO-8859-1"?>
<StyledLayerDescriptor version="1.0.0" 
                       xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd" 
                       xmlns="http://www.opengis.net/sld" 
                       xmlns:ogc="http://www.opengis.net/ogc" 
                       xmlns:xlink="http://www.w3.org/1999/xlink" 
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                       xmlns:gml="http://www.opengis.net/gml">
    <NamedLayer>
        <Name>Geomajas SLD Rulez</Name>
        <UserStyle>
            <Title>Render Naturflaechen aus dem WFS</Title>
            <FeatureTypeStyle>
                <Rule>
                    <PolygonSymbolizer>
                        <Fill>
                            <GraphicFill>
                                <Graphic>
                                    <Mark>
                                        <WellKnownName>shape://times</WellKnownName>
                                        <Stroke>
                                            <CssParameter name="stroke">#006600</CssParameter>
                                            <CssParameter name="stroke-width">1</CssParameter>
                                        </Stroke>
                                    </Mark>
                                    <Size>9</Size>
                                </Graphic>
                            </GraphicFill>
                            <!--CssParameter name="fill">#006600</CssParameter>
                            <CssParameter name="fill-opacity">0.8</CssParameter-->
                        </Fill>
                        <!--Stroke>
                            <CssParameter name="stroke">#FFFFFF</CssParameter>
                            <CssParameter name="stroke-width">2</CssParameter>
                        </Stroke-->
                    </PolygonSymbolizer>
                </Rule>
            </FeatureTypeStyle>
        </UserStyle>
    </NamedLayer>
</StyledLayerDescriptor>
