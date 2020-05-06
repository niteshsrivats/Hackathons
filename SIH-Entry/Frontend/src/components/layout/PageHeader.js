import React, {Component, Fragment} from 'react';
import {Button, Col, Input, Layout, Row, Typography} from 'antd';

const {Header} = Layout;
const {Search} = Input;
const {Title} = Typography;

class PageHeader extends Component {
    CreateButton = props => {
        if (this.props.button) {
            return (
                <Button type="primary" onClick={this.props.onClick}>
                    {this.props.button}
                </Button>
            );
        } else {
            return <Fragment/>;
        }
    };

    render() {
        return (
            <>
                <Header
                    style={{
                        background: '#fff',
                        borderBottom: '1px solid rgba(0,0,0,0.1)'
                    }}
                >
                    <Row type="flex" justify="space-around">
                        <Col>
                            <this.CreateButton/>
                        </Col>
                        <Col style={{display: 'flex', alignItems: 'center'}}>
                            <Title level={3}>Paperless.io</Title>
                        </Col>
                        <Col>
                            <Search
                                placeholder={this.props.search}
                                style={{width: 200}}
                            />
                        </Col>
                    </Row>
                </Header>
            </>
        );
    }
}

export default PageHeader;
