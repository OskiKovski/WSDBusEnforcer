import React, {Component} from 'react';
import CityMap from "./../citymap/CityMap";
import BusListView from "../list/BusListView";
import PoliceListView from "../list/PoliceListView";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";

class MainView extends Component {

    constructor(){
        super();
        this.state = {
            response: {
                buses: [],
                policeCars: [],
            }
        };

        this.setMock();

        this.url = 'http://localhost:8080/api/center/getLists';
        this.fetchUpdate = this.fetchUpdate.bind(this);
        this.fetchUpdate();
        setInterval(this.fetchUpdate, 5000);
    }

    setMock() {
        const data = {
            buses: ['Autobus 523', 'Autobus 162', 'Tramwaj 20'],
            policeCars: ['Komisarz Alex', 'W 11'],
        };

        new MockAdapter(axios).onGet(this.url).reply(200, data);
    }

    async fetchUpdate() {
        axios.get(this.url)
            .then(response => {
                this.setState({
                    response: response.data
                });
            })
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
                            <BusListView type={'BUS'} data={this.state.response.buses}/>
                        </div>
                        <div className="right-list-section">
                            <PoliceListView type={'POLICE'} data={this.state.response.policeCars}/>
                        </div>
                    </section>
                </div>
            </section>
        )
    }
}

export default MainView;
