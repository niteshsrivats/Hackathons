import React, { Component } from 'react';
import { PageHeader, Tabs, Button, Statistic, Descriptions } from 'antd';
import axios from 'axios';

const renderContent = (constables, column = 2) => (
    <Descriptions size="small" column={column}>
        <Descriptions.Item label="Assigned Constables">
            {constables}
        </Descriptions.Item>
    </Descriptions>
);

const extraContent = (
    <div
        style={{
            display: 'flex',
            width: 'max-content',
            justifyContent: 'flex-end'
        }}
    >
        <Statistic
            title="Status"
            value="Normal"
            style={{
                marginRight: 32
            }}
        />
    </div>
);

const Content = ({ children, extra }) => {
    return (
        <div className="content">
            <div className="main">{children}</div>
            <div className="extra">{extra}</div>
        </div>
    );
};

class CustomPageHeader extends Component {
    state = { beatDict: {} };

    componentDidMount = async () => {
        var beatDict = {};

        const beatResponse = await axios.get(
            'http://172.16.8.174:8080/v1/beats/station/1'
        );
        const beatData = beatResponse.data;
        const beats = beatData.map((beat) => {
            return {
                id: beat.id
            };
        });
        beats.forEach((beat) => {
            beatDict[beat.id] = 0;
        });
        const { data } = await axios.get(
            'http://172.16.8.174:8080/v1/officers/station/1?officerType=CONSTABLE'
        );
        data.forEach((officer) => {
            if (officer.beat) {
                if (beatDict.hasOwnProperty(officer.beat.id)) {
                    beatDict[officer.beat.id]++;
                } else {
                    beatDict[officer.beat.id] = 1;
                }
            }
        });

        this.setState({ beatDict });
    };
    render() {
        // console.log(this.props.id);
        return (
            <div>
                <PageHeader
                    style={{
                        border: '1px solid rgb(235, 237, 240)'
                    }}
                    onBack={() => window.history.back()}
                    title={'Beat #' + this.props.id}
                >
                    <Content extra={extraContent}>
                        {renderContent(this.state.beatDict[this.props.id])}
                    </Content>
                </PageHeader>
            </div>
        );
    }
}
export default CustomPageHeader;
