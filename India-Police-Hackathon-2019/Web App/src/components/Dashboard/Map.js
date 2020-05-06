import React, { Component } from 'react';
import {
    Polygon,
    Map,
    // InfoWindow,
    Marker,
    GoogleApiWrapper
} from 'google-maps-react';
// import { withRouter } from 'react-router-dom';
import { Row, Col } from 'antd';
import axios from 'axios';
// import { Router, Route, Link } from 'react-router-dom';
// import history from '../../history';

const style = {
    width: '100%',
    height: '100%',
    position: 'relative'
};
const pollingUrl = "http://172.16.8.174:8080/v1/beat-locations"
export class MapContainer extends Component {
    // constuctor(props) {
    //     this.routeChange = this.routeChange.bind(this);
    // }
    state = { beats: [], lat: 0.0, lng: 0.0, allLocs: [] };

    pollForPoliceResponse = async () => {
        const policeResponse = await axios.get(pollingUrl)
        const locationObj = policeResponse.data;

        const allLocs = Object.keys(locationObj).map((k) => {
            return { id: k, lat: locationObj[k][0], lng: locationObj[k][1] };
        });

        this.setState({ allLocs })

    }
    componentDidMount = async () => {
        const stationResponse = await axios.get(
            'http://172.16.8.174:8080/v1/stations/1'
        );
        const beatResponse = await axios.get(
            'http://172.16.8.174:8080/v1/beats/station/1'
        );


        setInterval(this.pollForPoliceResponse, 30000);


        // console.log(allLocs);



        const { latitude, longitude, name } = stationResponse.data;
        const { data } = beatResponse;
        const beats = data.map((beat) => {
            return {
                id: beat.id,
                coords: [
                    { lat: beat.location[0], lng: beat.location[1] },
                    { lat: beat.location[2], lng: beat.location[3] },
                    { lat: beat.location[4], lng: beat.location[5] },
                    { lat: beat.location[6], lng: beat.location[7] }
                ]
            };
        });
        this.setState({ beats, lat: latitude, lng: longitude, name });
    };
    render() {
        var i = 1;
        var k = 1;
        return (
            <Row>
                <Col xs={4} sm={4} md={4} lg={4} xl={4}></Col>
                <Col xs={16} sm={16} md={16} lg={16} xl={16}>
                    <h1>{this.state.name}</h1>
                    <div style={{ height: '70vh', width: '100%' }}>
                        <Map
                            google={this.props.google}
                            style={style}
                            initialCenter={{
                                lat: this.state.lat,
                                lng: this.state.lng
                            }}
                            center={{
                                lat: this.state.lat,
                                lng: this.state.lng
                            }}
                            zoom={16}
                        >
                            {/* <Marker
                                title={'Beat no. 1'}
                                position={{ lat: 12.923532, lng: 77.500234 }}
                            />
                            <Marker
                                name={'beat no. 2'}
                                position={{ lat: 12.923323, lng: 77.500434 }}
                            /> */}

                            {this.state.beats.map((beat) => {
                                return (
                                    <Polygon
                                        key={i++}
                                        paths={beat.coords}
                                        strokeColor="#123456"
                                        strokeOpacity={0.5}
                                        strokeWeight={2}
                                    />
                                );
                            })}

                            {this.state.allLocs.map((loc) => {
                                return (
                                    <Marker
                                        key={k++}
                                        name={loc.id}
                                        position={{ lat: loc.lat, lng: loc.lng }}
                                        icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/library_maps.png"
                                    />
                                );
                            })}


                        </Map>
                    </div>
                </Col>
                <Col xs={4} sm={4} md={4} lg={4} xl={4}></Col>
            </Row>
        );
    }
}

export default GoogleApiWrapper({
    apiKey: 'AIzaSyAMoQ2nGfPp2VU1PXxMuAQRaflYbZnSb88'
})(MapContainer);
