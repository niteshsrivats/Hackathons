import React, {Component, Fragment} from 'react';
import {List, Typography} from "antd";

const {Title} = Typography;

class Logger extends Component {
    data = [
        'Rohan Mayya\'s Library Card has been approved. Story moved to Completed.',
        'Nitesh Srivats (CSE Dept.) request has been declined. Story removed.',
        'Nitesh Srivats (CSE Dept.) story had been moved to Document Verification.',
        'Ananya G.M. (CSE Dept.) has applied for a new Digital Library Card. Story Created.',
        'Nitesh Srivats (CSE Dept.) has applied for a new Digital Libarary Card. Story Created.'
    ];

    render() {
        let i = 1;
        return (

            <div style={{
                padding: 10
            }}>
                < Title level={2}> Logger</Title>
                <List
                    size="small"
                    bordered={false}
                    loadMore
                    dataSource={this.data}
                    renderItem={(item) => (
                        <Fragment>
                            <List.Item key={i++}>
                                {item}
                            </List.Item>
                        </Fragment>
                    )}
                />
            </ div>
        );
    }
}

export default Logger;
