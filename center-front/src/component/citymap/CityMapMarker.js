import React, {Component} from 'react';
import {Marker, Popup} from 'react-leaflet';
import DivIcon from 'react-leaflet-div-icon'
import L from "leaflet";

class CityMapMarker extends Component {

    constructor(){
        super();
		this.hoverOn = this.hoverOn.bind(this);
		this.hoverOff = this.hoverOff.bind(this);
		this.getIcon = this.getIcon.bind(this);
    }
	
	hoverOn(e) {
		this.props.hoverHandler(this.props.id);
	}
	
	hoverOff(e) {
		this.props.hoverHandler(null);
	}
	
    getIcon() {
		let iconClassName = "leaflet-div-icon";
		const labelClassNames = {
			bus: "bus-label",
			availablePoliceUnit: "police-unit-label-available",
			unavailablePoliceUnit: "police-unit-label-unavailable"
		};
		let labelClassName = labelClassNames[this.props.type];
		if (this.props.active) {
			iconClassName += " icon-active";
			labelClassName += " label-outline";
		}
		return new L.DivIcon({
			className: iconClassName,
			iconSize: new L.Point(15, 15),
			html: `<span class="${labelClassName}">${this.props.id}</span>`
		});
	}

    render() {
		const pos = this.props.position;
        return (
			<Marker
				onmouseover={this.hoverOn}
				onmouseout={this.hoverOff}
				key={this.props.id}
				position={pos}
				icon={this.getIcon()}>
				<Popup>({pos.lat}, {pos.lon})</Popup>
			</Marker>
        );
    }
}

export default CityMapMarker;
