import React, {Component} from 'react';
import CityMap from "./../citymap/CityMap";
import ListView from "../list/ListView";
import BusListView from "../list/BusListView";
import PoliceListView from "../list/PoliceListView";

class MainView extends Component {

    constructor(){
        super();
    }

    render() {
        return (
            <section className="side-by-side-container">
                <div className="map-section">
                    <CityMap/>
                </div>
                <div className="list-container-section">
                    <section className="lists-container">
                        <div className="left-list-section">
                            <BusListView type={'BUS'}/>
                        </div>
                        <div className="right-list-section">
                            <PoliceListView type={'POLICE'}/>
                        </div>
                    </section>
                </div>
            </section>


        )
    }
}

export default MainView;
