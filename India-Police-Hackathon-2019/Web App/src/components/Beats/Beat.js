import React, { Component, Fragment } from 'react';
import { Row, Col, List, Tabs } from 'antd';
import CustomPageHeader from './CustomPageHeader';
// import BeatBook from /";
import BeatBook from './BeatBook';
import graph1 from '../../images/graph1.png';
import graph2 from '../../images/graph2.jpg';

// Beat class - boundary, id, station,
// hotspots,
const { TabPane } = Tabs;

class Beat extends Component {
    state = { id: null };
    componentWillMount() {
        var id = this.props.match.params.beatid;
        this.setState({ id });
    }
    render() {
        return (
            <Fragment>
                <div>
                    <CustomPageHeader id={this.state.id} />

                    <Tabs
                        defaultActiveKey="1"
                        style={{
                            marginTop: 20,
                            marginLeft: 50,
                            marginRight: 50
                        }}
                    >
                        <TabPane
                            tab="Statistics"
                            key="1"
                            style={{ marginTop: 30 }}
                        >
                            <img src={graph1} height="300" width="400" />
                            <img src={graph2} height="300" width="400" />
                        </TabPane>
                        <TabPane tab="Logs" key="2">
                            <BeatBook id={this.state.id}/>
                        </TabPane>
                        <TabPane tab="Criminals" key="3">
                            <BeatBook id={this.state.id}/>
                        </TabPane>
                        <TabPane tab="Crimes" key="4">
                            <BeatBook id={this.state.id}/>
                        </TabPane>
                    </Tabs>
                    {/* <List
                        size="small"
                        header={<div>Header</div>}
                        footer={<div>Footer</div>}
                        bordered
                        dataSource={data}
                        renderItem={(item) => <List.Item>{item}</List.Item>}
                    /> */}

                    {/* <h2 style={{ textAlign: "center", marginTop: 20 }}>Beat 1</h2>
          <Row style={{ marginTop: 20 }}>
            <Col sm={4}></Col>
            <Col
              style={{ backgroundColor: "gray", color: "white" }}
              sm={6}
            >
              <img src={graph1} height="200" width="400" />
            </Col>
            <Col sm={4}></Col>
            <Col
              style={{ backgroundColor: "gray", height: 200, color: "white" }}
              sm={6}
            >
                <img src={graph2} height="200" width="400" />
            </Col>
            <Col sm={4}></Col>
          </Row>

          <p style={{ textAlign: "center", marginTop: 30, marginBottom: 30 }}>
            Number of crimes over past 30 days.
          </p>

          <h3 style={{ textAlign: 'center' }}>Logs</h3>
          <Row style={{ marginTop: 20 }}>
            <Col sm={4}></Col>
            <Col xs={20} sm={16} md={12} lg={8} xl={12}>
              <List
                itemLayout="horizontal"
                dataSource={data}
                renderItem={item => (
                  <List.Item>
                    <List.Item.Meta
                      description={item.description}
                      title={item.title}
                    />
                    <div></div>
                  </List.Item>
                )}
              />
            </Col>
            <Col sm={4}></Col>
          </Row> */}
                </div>
            </Fragment>
        );
    }
}

export default Beat;
