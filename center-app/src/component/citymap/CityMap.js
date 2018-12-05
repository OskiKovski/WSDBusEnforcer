import React, { Component } from 'react';
import { render } from 'react-dom';
import { Map, Marker, Popup, TileLayer } from 'react-leaflet';
import {policeIcon} from '../icon/policeIcon.js';
import {busIcon} from "../icon/busIcon";

class CityMap extends Component {

    render() {
        const stamenTonerTiles = 'http://stamen-tiles-{s}.a.ssl.fastly.net/toner-background/{z}/{x}/{y}.png';
        const stamenTonerAttr = 'Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
        const mapCenter = [52.272916, 21.046223];
        const zoomLevel = 12;
        const markerPolicePos = [52.272916, 21.046223];
        const markerBusPos = [52.273441, 21.044493];

        return (
            <div>
                <Map
                    center={mapCenter}
                    zoom={zoomLevel}
                >
                    <TileLayer
                        attribution={stamenTonerAttr}
                        url={stamenTonerTiles}
                    />
                    <Marker
                        position={markerPolicePos}
                        icon={ policeIcon }>
                    </Marker>
                    <Marker
                        position={markerBusPos}
                        icon={ busIcon }>
                    </Marker>
                </Map>
            </div>
        );
    }
}

export default CityMap;
