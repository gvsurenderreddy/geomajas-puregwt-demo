<?xml version="1.0" encoding="ISO-8859-1"?>
<StyledLayerDescriptor version="1.0.0" 
                       xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd" 
                       xmlns="http://www.opengis.net/sld" 
                       xmlns:ogc="http://www.opengis.net/ogc" 
                       xmlns:xlink="http://www.w3.org/1999/xlink" 
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                       xmlns:gml="http://www.opengis.net/gml">
    <NamedLayer>
        <Name>hashes13</Name>
        <UserStyle>
            <FeatureTypeStyle>
                <Name>name</Name>
                <Rule>
                    <MinScaleDenominator>80000</MinScaleDenominator>
                    <PolygonSymbolizer>
                        <Fill>
                            <CssParameter name="fill">#006600</CssParameter>
                            <CssParameter name="fill-opacity">1</CssParameter>
                        </Fill>
                    </PolygonSymbolizer>
                </Rule>
                <Rule>
                    <MaxScaleDenominator>80000</MaxScaleDenominator>
                    <PolygonSymbolizer>
                        <Fill>
                            <CssParameter name="fill">#006600</CssParameter>
                            <CssParameter name="fill-opacity">0.8</CssParameter>
                        </Fill>
                        <Stroke>
                            <CssParameter name="stroke">#FFFFFF</CssParameter>
                            <CssParameter name="stroke-width">2</CssParameter>
                        </Stroke>
                    </PolygonSymbolizer>
                    <PolygonSymbolizer>
                        <Fill>
                            <GraphicFill>
                                <Graphic>
                                    <Mark>
                                        <WellKnownName>shape://backslash</WellKnownName>
                                        <Stroke>
                                            <CssParameter name="stroke">#FFFFFF</CssParameter>
                                        </Stroke>
                                    </Mark>
                                    <Size>
                                        <ogc:Literal>8.0</ogc:Literal>
                                    </Size>
                                    <Rotation>
                                        <ogc:Literal>55.0</ogc:Literal>
                                    </Rotation>
                                </Graphic>
                            </GraphicFill>
                        </Fill>
                    </PolygonSymbolizer>
                    <PolygonSymbolizer>
                        <Fill>
                            <GraphicFill>
                                <Graphic>
                                    <Mark>
                                        <WellKnownName>shape://backslash</WellKnownName>
                                        <Stroke>
                                            <CssParameter name="stroke">#FFFFFF</CssParameter>
                                        </Stroke>
                                    </Mark>
                                    <Size>
                                        <ogc:Literal>8.0</ogc:Literal>
                                    </Size>
                                </Graphic>
                            </GraphicFill>
                        </Fill>
                    </PolygonSymbolizer>
                    <TextSymbolizer>
                        <Label>
                            <ogc:PropertyName>Gebietsname</ogc:PropertyName>
                        </Label>
                        <Halo>
                            <Radius>3</Radius>
                            <Fill>
                                <CssParameter name="fill">#FFFFFF</CssParameter>
                            </Fill>
                        </Halo>
                    </TextSymbolizer>
                </Rule>
            </FeatureTypeStyle>
        </UserStyle>
    </NamedLayer>
</StyledLayerDescriptor>  