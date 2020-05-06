import React, { Component, Fragment } from 'react';
import axios from 'axios';
import {
    Row,
    Col,
    List
    // Tag
} from 'antd';
import { Link } from 'react-router-dom';

class Beats extends Component {
    state = { beatList: [] };

    componentDidMount = async () => {
        var d = {};

        const beatResponse = await axios.get(
            'http://172.16.8.174:8080/v1/beats/station/1'
        );
        const beatData = beatResponse.data;
        const beats = beatData.map((beat) => {
            return {
                id: beat.id
            };
        });
        beats.forEach((beat)=>{
            d[beat.id] = 0;
        });
        const { data } = await axios.get(
            'http://172.16.8.174:8080/v1/officers/station/1?officerType=CONSTABLE'
        );
        data.forEach((officer) => {
            if (officer.beat) {
                if (d.hasOwnProperty(officer.beat.id)) {
                    d[officer.beat.id]++;
                } else {
                    d[officer.beat.id] = 1;
                }
            }
        });
        var beatList = [];
        Object.keys(d).forEach(function(key) {
            beatList.push({ id: key, constables: d[key] });
        });
        this.setState({ beatList });
        console.log(beatList);
    };
    render() {
        return (
            <Row>
                <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
                <Col xs={20} sm={16} md={12} lg={8} xl={12}>
                    <List
                        itemLayout="horizontal"
                        dataSource={this.state.beatList}
                        renderItem={(item) => {
                            const beatLink = `/beat/${item.id}`;
                            return (
                                <Link to={beatLink}>
                                    <List.Item>
                                        <List.Item.Meta
                                            description={
                                                'No. of constables: ' +
                                                item.constables
                                            }
                                            title={'Beat #' + item.id}
                                        />
                                    </List.Item>
                                </Link>
                            );
                        }}
                    />
                </Col>
                <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
            </Row>
        );
    }
}

export default Beats;
