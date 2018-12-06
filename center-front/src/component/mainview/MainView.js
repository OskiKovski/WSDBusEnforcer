import React, {Component} from 'react';
import CityMap from "./../citymap/CityMap";
import BusListView from "../list/BusListView";
import PoliceListView from "../list/PoliceListView";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";
import {url} from "../Constants.js"

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

        this.fetchUpdate = this.fetchUpdate.bind(this);
        this.fetchUpdate();
        setInterval(this.fetchUpdate, 5000);
    }

    setMock() {
        const data = {
            buses: [
                {
                    id: '523',
                    name: 'Autobus 523',
                    position: [52.253354, 20.996054]
                },
                {
                    id: '162',
                    name: 'Autobus 162',
                    position: [52.252106, 20.998135]
                },
                {
                    id: '20',
                    name: 'Tramwaj 20',
                    position: [52.253866, 21.000045]
                }
            ],
            policeCars: [
                {
                    id: '007',
                    name: '007',
                    position: [52.255094, 20.997438]
                },
                {
                    id: 'W11',
                    name: 'W 11',
                    position: [52.254923, 20.995979]
                }
            ]
        };

        new MockAdapter(axios).onGet(url).reply(200, data);
    }

    async fetchUpdate() {
        axios.get(url)
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
                    <CityMap data={this.state.response}/>
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
