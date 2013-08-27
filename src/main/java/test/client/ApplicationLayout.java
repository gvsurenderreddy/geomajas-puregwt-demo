/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2013 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package test.client;

import org.geomajas.geometry.Coordinate;
import org.geomajas.puregwt.client.GeomajasGinjector;
import org.geomajas.puregwt.client.event.LayerHideEvent;
import org.geomajas.puregwt.client.event.LayerShowEvent;
import org.geomajas.puregwt.client.event.LayerVisibilityHandler;
import org.geomajas.puregwt.client.event.LayerVisibilityMarkedEvent;
import org.geomajas.puregwt.client.event.MapInitializationEvent;
import org.geomajas.puregwt.client.event.MapInitializationHandler;

import org.geomajas.puregwt.client.event.FeatureSelectedEvent;
import org.geomajas.puregwt.client.event.FeatureSelectionHandler;

import org.geomajas.puregwt.client.map.MapConfiguration;
import org.geomajas.puregwt.client.map.MapPresenter;
import org.geomajas.puregwt.client.map.layer.Layer;

//import org.geomajas.puregwt.widget.client.featureselectbox.event.FeatureClickedEvent;
//import org.geomajas.puregwt.widget.client.featureselectbox.event.FeatureClickedHandler;
//import org.geomajas.puregwt.widget.client.featureselectbox.presenter.impl.FeatureClickedListener;
import org.geomajas.puregwt.client.controller.FeatureSelectionController;
import org.geomajas.puregwt.client.controller.FeatureSelectionController.SelectionMethod;

import org.geomajas.puregwt.client.event.FeatureDeselectedEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.ui.Button;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geomajas.layer.feature.Attribute;
import org.geomajas.puregwt.client.map.feature.Feature;
import org.geomajas.puregwt.client.map.layer.FeaturesSupported;

/**
 * Application layout.
 *
 * @author Pieter De Graef
 */
public class ApplicationLayout extends Composite {

    /**
     * UI binder interface for the application layout.
     *
     * @author Pieter De Graef
     */
    interface ApplicationLayoutUiBinder extends UiBinder<Widget, ApplicationLayout> {
    }
    private static final ApplicationLayoutUiBinder UI_BINDER = GWT.create(ApplicationLayoutUiBinder.class);
    
    private static final GeomajasGinjector INJECTOR = GWT.create(GeomajasGinjector.class);
    
    private final MapPresenter mapPresenter;
    
    @UiField
    protected SimplePanel contentPanel;
    
    @UiField
    protected VerticalPanel layerCheckBoxLayout;
   
    @UiField
    protected VerticalPanel layerGetFeatureInfo;
    
    @UiField
    protected VerticalPanel getFeatureInfoButtons;
    
    @UiField
    protected VerticalPanel layerEventLayout;
    
    private FeatureSelectionController featureSelectionController;
    
    // private FeatureClickedListener clickListener = null;
    com.google.web.bindery.event.shared.HandlerRegistration addFeatureSelectionHandler = null;
    
    private final Logger logger;

    public ApplicationLayout() {
        logger = Logger.getLogger("PureGWTLogger");
        logger.log(Level.INFO, "init GWT Application Layout");

        initWidget(UI_BINDER.createAndBindUi(this));
        mapPresenter = INJECTOR.getMapPresenter();

        logger.log(Level.INFO, "init geomajas mapresenter");
        mapPresenter.initialize("app", "mapOsm");
        mapPresenter.getConfiguration().setMapHintValue(MapConfiguration.ANIMATION_TIME, 300L);
        
        featureSelectionController = new FeatureSelectionController();        
        initMapListeners();

        ResizableMapLayout mapLayout = new ResizableMapLayout(mapPresenter);
        contentPanel.setWidget(mapLayout);
        initGetFeatureBtn();

    }

    private void initMapListeners() {
        //todo put handler to class variable, do not instantiate new obj each time
        mapPresenter.getEventBus().addMapInitializationHandler(new MyMapInitializationHandler());
        mapPresenter.getEventBus().addLayerVisibilityHandler(new MyLayerVisibilityHandler());

//      mapPresenter.getEventBus().addHandler(FeatureClickedHandler.TYPE, new VectorLayerClickedHandler());
        mapPresenter.getEventBus().addHandler(FeatureSelectionHandler.TYPE, new VectorFeatureSelectionHandler());        
//      mapPresenter.getEventBus().addHandler(FeatureClickedHandler.TYPE, new VectorLayerClickedHandler());
//      mapPresenter.getEventBus().addHandler(FeatureSelectionHandler.TYPE, new VectorFeatureSelectionHandler());
        
        // feature selection handler
        //mapPresenter.getEventBus().addFeatureSelectionHandler(new DeegreeWFSFeatureSelectionHandler());
    }

    private void displayAttrs(Feature feature) {
            logger.log(Level.INFO, "get info for feature : " + feature.toString());
            for (Map.Entry<String, Attribute<?>> entry : feature.getAttributes().entrySet()) {
                logger.log(Level.INFO,
                        "attributes : " + entry.getKey()
                        + " - " + entry.getValue().toString());
                layerGetFeatureInfo.add(new Label(entry.getKey() + " = " + entry.getValue().toString()));
            }        
    }
    
    private void disableHandler() {
        featureSelectionController.setSelectionMethod(null);
        mapPresenter.setMapController(null);
        
//        if (addFeatureSelectionHandler != null) {
//            addFeatureSelectionHandler.removeHandler();
//            addFeatureSelectionHandler = null;
//        }   
//        if (clickListener != null) {
//            //mapPresenter.removeMapListener(clickListener);
//            clickListener = null;
//        }
    }
    
    private void clearSelection() {
        layerGetFeatureInfo.clear();
        FeaturesSupported fs = (FeaturesSupported)mapPresenter.getLayersModel().getLayer("clientLayerDeegree");
        fs.clearSelectedFeatures();
    }
    
    /**
     * Handler that handles FeatureClickedEvent.
     */
//    private class VectorLayerClickedHandler implements FeatureClickedHandler {
//        public void onFeatureClicked(FeatureClickedEvent event) {
//            logger.log(Level.SEVERE, "trigger onFeatureClicked");
//            
//            clearSelection();
//            Feature feature = event.getFeature();
//            FeaturesSupported fs = (FeaturesSupported)feature.getLayer();
//            
//            //logger.log(Level.INFO, feature.toString());
//            if ( feature.isSelected() ) {
//                fs.deselectFeature(feature);
//                clearSelection();
//            } else {
//                fs.selectFeature(feature);
//            }
//        }    
//    }
    
    /**
     * Handler for feature selection events (click on a feature ...)
     */
    private class VectorFeatureSelectionHandler implements FeatureSelectionHandler {

        @Override
        public void onFeatureSelected(FeatureSelectedEvent event) {
            logger.log(Level.SEVERE, "trigger onFeatureSelected");
            
            Feature feature = event.getFeature();
            displayAttrs(feature);
            
//          FeaturesSupported fs = (FeaturesSupported)feature.getLayer();
//          fs.selectFeature(feature); 
//          FeaturesSupported fs = (FeaturesSupported)mapPresenter.getLayersModel().getLayer("clientLayerDeegree");

        }

        @Override
        public void onFeatureDeselected(FeatureDeselectedEvent event) {
            layerGetFeatureInfo.clear();
        }
    }

    /*
     * init getFeature Buttons ...
     */
    private void initGetFeatureBtn() {
        
        // TODO user @UiHandler("myCustomBtn")!!!
        Button getFeatureClickOnlyBtn = new Button("Click");
        getFeatureInfoButtons.add(getFeatureClickOnlyBtn);
        getFeatureClickOnlyBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                clearSelection();
                disableHandler();
//                FeatureClickedListener clickListener; //= new FeatureClickedListener(32);
//                mapPresenter.addMapListener(clickListener);
                //featureSelectionController.setSelectionMethod(SelectionMethod.CLICK_ONLY);
                //mapPresenter.addMapListener(featureSelectionController);
            }
        });

        Button getFeatureDragDropBtn = new Button("Drag & Drop");
        getFeatureInfoButtons.add(getFeatureDragDropBtn);
        getFeatureDragDropBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                clearSelection();
                disableHandler();
                // default click handel
                //addFeatureSelectionHandler = mapPresenter.getEventBus().addFeatureSelectionHandler(new VectorFeatureSelectionHandler());
                featureSelectionController.setSelectionMethod(SelectionMethod.CLICK_AND_DRAG);
                mapPresenter.setMapController(featureSelectionController);

            }
        });

        Button getFeatureClearBtn = new Button("deaktivieren");
        getFeatureInfoButtons.add(getFeatureClearBtn);
        getFeatureClearBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                clearSelection();
                disableHandler();
            }
        });        

    }
    
    /**
     * Map initialization handler that adds a CheckBox to the layout for every
     * layer. With these CheckBoxes, the user can toggle the layer's visibility.
     *
     * @author Pieter De Graef
     */
    private class MyMapInitializationHandler implements MapInitializationHandler {

        @Override
        public void onMapInitialized(MapInitializationEvent event) {
            // When the map initializes: add a CheckBox for every layer, so the use can toggle visibility:
            for (int i = 0; i < mapPresenter.getLayersModel().getLayerCount(); i++) {
                final Layer layer = mapPresenter.getLayersModel().getLayer(i);
                CheckBox layerCheck = new CheckBox(layer.getTitle());
                layerCheck.setValue(layer.isMarkedAsVisible());

                // When CheckBox value changes, change the layer's visibility as well:
                layerCheck.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        if (event.getValue() != null) {
                            layer.setMarkedAsVisible(event.getValue());
                        }
                    }
                });
                layerCheckBoxLayout.add(layerCheck);
            }

            logger.log(Level.INFO,
                    "current scale = " + mapPresenter.getViewPort().getScale()
                    + " and postion = {1}" + mapPresenter.getViewPort().getPosition());

            double scale = mapPresenter.getViewPort().getScale();
            //System.out.println("mapPresenter in CRS: " + mapPresenter.getViewPort().getCrs());
            mapPresenter.getViewPort().applyScale(0.026165329093358645);
            mapPresenter.getViewPort().applyPosition(new Coordinate(1486924.7339172666, 6605344.017777634));

            logger.log(Level.INFO,
                    "new current scale = " + mapPresenter.getViewPort().getScale()
                    + " and postion = {1}" + mapPresenter.getViewPort().getPosition());

            //logger.log( Level.INFO, "adding map listener" );
            //mapPresenter.addMapListener( featureSelectionController );
        }
    }

    /**
     * Handler that catches layer visibility events and prints them out.
     *
     * @author Pieter De Graef
     */
    private class MyLayerVisibilityHandler implements LayerVisibilityHandler {

        @Override
        public void onShow(LayerShowEvent event) {
            layerEventLayout.add(new Label("onShow: " + event.getLayer().getTitle()));
        }

        @Override
        public void onHide(LayerHideEvent event) {
            layerEventLayout.add(new Label("onHide: " + event.getLayer().getTitle()));
        }

        @Override
        public void onVisibilityMarked(LayerVisibilityMarkedEvent event) {
            layerEventLayout.add(new Label("onVisibilityMarked: " + event.getLayer().getTitle()));
        }
    }
}