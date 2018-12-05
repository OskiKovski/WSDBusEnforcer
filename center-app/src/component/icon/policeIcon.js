import L from 'leaflet';

const policeIcon = new L.Icon({
    iconUrl: require('./policeIcon.png'),
    iconRetinaUrl: require('./policeIcon.png'),
    iconAnchor: null,
    popupAnchor: null,
    shadowUrl: null,
    shadowSize: null,
    shadowAnchor: null,
    iconSize: new L.Point(40, 40)
});

export { policeIcon };