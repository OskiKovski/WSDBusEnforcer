import React, {Component, Fragment} from 'react';
import {Map, Marker, TileLayer} from 'react-leaflet';
import L from "leaflet";
import CityMapMarker from './CityMapMarker';

class CityMap extends Component {

    constructor() {
        super();

        this.stamenTonerTiles = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
        this.stamenTonerAttr = 'Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
        this.mapCenter = [52.253682, 20.997803];
        this.zoomLevel = 12;
    }

	getMarkersOfType(elems, type) {
		return elems.map(elem => {
			return <CityMapMarker
				type={type}
				id={elem.id}
				position={elem.position}
				active={elem.id===this.props.data.hovered}
				hoverHandler={this.props.hoverHandler}>
			</CityMapMarker>
		});
	}		
	
    getPoliceMarkers() {
        return this.getMarkersOfType(this.props.data.policeUnits, "policeUnit");
    }

    getBusMarkers() {
        return this.getMarkersOfType(this.props.data.buses, "bus");
    }

    getMarkers() {
        return <Fragment>
            {this.getBusMarkers()}
            {this.getPoliceMarkers()}
        </Fragment>
    }

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
