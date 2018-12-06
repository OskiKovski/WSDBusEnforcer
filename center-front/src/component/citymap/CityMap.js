import React, {Component} from 'react';
import {Map, Marker, TileLayer} from 'react-leaflet';
import L from "leaflet";

class CityMap extends Component {

    constructor(){
        super();

        this.stamenTonerTiles = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
        this.stamenTonerAttr = 'Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
        this.mapCenter = [52.253682, 20.997803];
        this.zoomLevel = 12;
    }

    getPoliceMarkers = () => {
        return (
            this.props.data.policeCars.map(elem => {
                const policeIcon = new L.DivIcon({
                    iconSize: new L.Point(0, 0),
                    html: '<span style="color:white;font-size:40px;background:blue">' + elem.id + '</span>'
                });

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
                const busIcon = new L.DivIcon({
                    iconSize: new L.Point(0, 0),
                    html: '<span style="color:black;font-size:40px;background:orange">' + elem.id + '</span>'
                });

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
        return (
            <div>
                <Map
                    center={this.mapCenter}
                    zoom={this.zoomLevel}
                >
                    <TileLayer
                        attribution={this.stamenTonerAttr}
                        url={this.stamenTonerTiles}
                    />
                    {this.getMarkers()}
                </Map>
            </div>
        );
    }
}

export default CityMap;
