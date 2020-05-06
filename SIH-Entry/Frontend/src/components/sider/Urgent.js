import React, {Component, Fragment} from 'react';
import {List, Typography} from "antd";

const {Title} = Typography;

class Urgent extends Component {
    data = [
        'Rohan\'s documents have been approved. E-card to be issued.',
        'Nitesh Srivats\'s request has been denied by the Head Of Department. Application to be cleared.'
    ];

    render() {
        let i = 1;
        return (
            <div style={{
                margin: '10px'
            }}>
                < Title level={2}> Alerts</Title>
                <List
                    size="small"
                    bordered={false}
                    loadMore
                    dataSource={this.data}
                    renderItem={(item) => (
                        <Fragment>
                            {/* <div style={{ color: '#FFF' }}> */}
                            <List.Item key={i++}>
                                {item}
                            </List.Item>
                            {/* </div> */}
                            {/* <br /> */}
                        </Fragment>
                    )}
                />
            </div>
        );
    }
}

export default Urgent;
