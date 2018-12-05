import React, {Component} from 'react';
import {Map, Marker, TileLayer} from 'react-leaflet';
import {policeIcon} from '../icon/policeIcon.js';
import {busIcon} from "../icon/busIcon";

class CityMap extends Component {

    getPoliceMarkers = () => {
        return (
            this.props.data.policeCars.map(elem => {
                return <Marker
                    key={elem.id}
                    position={elem.position}
                    icon={ policeIcon }>
                </Marker>
            })
        )
    };

    getBusMarkers = () => {
        return (
            this.props.data.buses.map(elem => {
                return <Marker
                    key={elem.id}
                    position={elem.position}
                    icon={ busIcon }>
                </Marker>
            })
        )
    };

    getMarkers = () => {
        return <div>
            {this.getBusMarkers()}
            {this.getPoliceMarkers()}
        </div>
    };

    render() {
        const stamenTonerTiles = 'http://stamen-tiles-{s}.a.ssl.fastly.net/toner-background/{z}/{x}/{y}.png';
        const stamenTonerAttr = 'Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
        const mapCenter = [52.253682, 20.997803];
        const zoomLevel = 12;

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
                    {this.getMarkers()}
                </Map>
            </div>
        );
    }
}

export default CityMap;
