import React, {Fragment, Component} from 'react';
import {
  AsyncStorage,
  Platform,
  Alert,
  View,
  Text,
  Button,
  TouchableOpacity,
  PermissionsAndroid,
} from 'react-native';

import axios from 'axios';

class StartBeat extends Component {
  componentDidMount() {
  }

 

  startBeat = async () => {
    try {
      // location
      const officerId = '400';

      const response = await axios.post(
        `http://172.16.8.174:8080/v1/beat-reports/${officerId}`,
        {},
      );

      var {id} = response.data;
      id = id + '';
      console.log(id);
      await AsyncStorage.setItem('beatToken', id);

      this.props.navigation.navigate('App');
    } catch (err) {
      console.log(err);
      Alert.alert('An error occurred');
    }
  };

  render() {
    return (
      <Fragment>
        <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
          <Button title="Start Beat" onPress={this.startBeat} />
        </View>
      </Fragment>
    );
  }
}

export default StartBeat;
