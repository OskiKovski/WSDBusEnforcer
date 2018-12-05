import L from 'leaflet';

const busIcon = new L.Icon({
    iconUrl: require('./busIcon.png'),
    iconRetinaUrl: require('./busIcon.png'),
    iconAnchor: null,
    popupAnchor: null,
    shadowUrl: null,
    shadowSize: null,
    shadowAnchor: null,
    iconSize: new L.Point(40, 40)
});

export { busIcon };