import React, { Component, Fragment } from 'react';
import { Menu, Dropdown, Button, Tag, Row, Col, List, Avatar } from 'antd';
import axios from 'axios';

// const { Option } = Select;
import avatar from '../../images/avatar.jpg';

class MyDropdown extends Component {
    menu = () => {
        var j = 1;
        console.log(this.props.id);
        return (
            <Menu>
                {this.props.beats.map((beat) => (
                    <Menu.Item
                        onClick={async () => {
                            const updateData = await axios.put(
                                'http://172.16.8.174:8080/v1/officers/' +
                                    this.props.id,
                                [beat.id]
                            );
                            this.props.handleUpdatedBeatForConstable(
                                this.props.id,
                                beat.id
                            );
                        }}
                        key={j++}
                        beat={beat.id}
                        id={this.props.id}
                    >
                        {beat.id}
                    </Menu.Item>
                ))}
            </Menu>
        );
    };

    render() {
        return (
            <div>
                <Dropdown overlay={this.menu} placement="bottomCenter">
                    <Button>Assign</Button>
                </Dropdown>
            </div>
        );
    }
}

class Officers extends Component {
    handleUpdatedBeatForConstable = (officerId, beatId) => {
        const { officersArray } = this.state;
        var newOfficersArray = [];
        var officers = officersArray.forEach((officer) => {
            if (officer.id == officerId) {
                newOfficersArray.push({
                    name: officer.name,
                    id: officerId,
                    beatId: beatId
                });
            } else {
                newOfficersArray.push(officer);
            }
        });

        this.setState({ officersArray: newOfficersArray });
    };
    state = { officersArray: [], beats: [] };
    componentDidMount = async () => {
        const { data } = await axios.get(
            'http://172.16.8.174:8080/v1/officers/station/1?officerType=CONSTABLE'
        );
        const officersArray = data.map((officer) => {
            return {
                id: officer.id,
                name: officer.name,
                beatId: officer.beat ? officer.beat.id : null
            };
        });
        const beatResponse = await axios.get(
            'http://172.16.8.174:8080/v1/beats/station/1'
        );
        const beatData = beatResponse.data;
        const beats = beatData.map((beat) => {
            return {
                id: beat.id
            };
        });
        // console.log(officersArray);
        // const beatResponse = await axios.get(
        //     'http://172.16.8.174:8080/v1/beats/station/1'
        // );
        // const { latitude, longitude, name } = stationResponse.data;
        // const { data } = beatResponse;
        // const beats = data.map((beat) => {
        //     return {
        //         id: beat.id,
        //         coords: [
        //             { lat: beat.location[0], lng: beat.location[1] },
        //             { lat: beat.location[2], lng: beat.location[3] },
        //             { lat: beat.location[4], lng: beat.location[5] },
        //             { lat: beat.location[6], lng: beat.location[7] }
        //         ]
        //     };
        // });
        this.setState({ officersArray, beats });
        // console.log(latitude, longitude, beats);
    };

    render() {
        return (
            <Fragment>
                <Row>
                    <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
                    <Col xs={20} sm={16} md={12} lg={8} xl={12}>
                        <List
                            itemLayout="horizontal"
                            dataSource={this.state.officersArray}
                            renderItem={(item) => {
                                const desc = item.beatId
                                    ? 'Beat # ' + item.beatId
                                    : '';
                                return (
                                    <List.Item>
                                        <List.Item.Meta
                                            avatar={<Avatar src={avatar} />}
                                            title={item.name}
                                            description={desc}
                                        />

                                        <div>
                                            {!item.beatId && (
                                                <Tag color="gold">Idle</Tag>
                                            )}
                                            {!item.beatId && (
                                                <MyDropdown
                                                    id={item.id}
                                                    beats={this.state.beats}
                                                    handleUpdatedBeatForConstable={
                                                        this
                                                            .handleUpdatedBeatForConstable
                                                    }
                                                />
                                            )}
                                            {item.beatId && (
                                                <Tag color="green">On Beat</Tag>
                                            )}
                                            {item.beatId && (
                                                <Button
                                                    type="primary"
                                                    id={item.id}
                                                    onClick={async () => {
                                                        const updateData = await axios.put(
                                                            'http://172.16.8.174:8080/v1/officers/' +
                                                                item.id,
                                                            []
                                                        );
                                                        this.handleUpdatedBeatForConstable(
                                                            item.id,
                                                            null
                                                        );
                                                    }}
                                                >
                                                    Unassign
                                                </Button>
                                            )}
                                        </div>
                                    </List.Item>
                                );
                            }}
                        />
                    </Col>
                    <Col xs={2} sm={4} md={6} lg={8} xl={6}></Col>
                </Row>
            </Fragment>
        );
    }
}

export default Officers;
