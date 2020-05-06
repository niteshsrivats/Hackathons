import React, {Component, Fragment} from 'react';
import {
  Alert,
  AsyncStorage,
  Button,
  View,
  Text,
  TextInput,
  Picker,
} from 'react-native';
import axios from 'axios';

class IllegalActivity extends Component {
  state = {type: 'GAMBLING', description: ''};
  // types: GAMBLING, PROSTITUTION, SALE_OF_ALCOHOL, SALE_OF_CIGARETTE
  submit = async () => {
    const {navigate} = this.props.navigation;
    const {type, description} = this.state;
    const obj = {
      description,
      beatIllegalActivitiesType: type,
    };
    const beatReportId = await AsyncStorage.getItem('beatToken');
    axios
      .post(
        `http://172.16.8.174:8080/v1/beat-reports/${beatReportId}/illegal-activities`,
        obj,
      )
      .then(response => {
        Alert.alert('Successfully recorded response');
        navigate('Home');
      })
      .catch(err => Alert.alert('Could not submit form'));
  };

  render() {
    return (
      <Fragment>
        <View style={{flex: 1, padding: 20}}>
          <Text style={{textAlign: 'center', fontWeight: 'bold'}}>
            Illegal Activity Report
          </Text>
          <Picker
            selectedValue={this.state.type}
            style={{height: 50, width: 300, marginVertical: 20}}
            onValueChange={(itemValue, itemIndex) =>
              this.setState({type: itemValue})
            }>
            <Picker.Item label="Gambling" value="GABMLING" />
            <Picker.Item label="Prostitution" value="PROSTITUTION" />
            <Picker.Item label="Sale of Alcohol" value="SALE_OF_ALCOHOL" />
            <Picker.Item label="Sale of Cigarette" value="SALE_OF_CIGARETTE" />
          </Picker>
          <TextInput
            style={{
              height: 40,
              borderColor: 'gray',
              borderWidth: 1,
              marginVertical: 20,
            }}
            onChangeText={description => this.setState({description})}
            value={this.state.description}
            placeholder="Enter description"
          />
          <Button onPress={this.submit} title="Submit" />
        </View>
      </Fragment>
    );
  }
}

export default IllegalActivity;
