import React from 'react';
import { createAppContainer, createSwitchNavigator } from "react-navigation";
import { createStackNavigator } from "react-navigation-stack";
import { View, Text, ActivityIndicator, StatusBar, AsyncStorage } from 'react-native';

import ExpectationReport from "./src/components/Reports/ExpectationReport";
import IllegalActivity from "./src/components/Reports/IllegalActivityReport";
import LawAndOrderReport from "./src/components/Reports/LawAndOrderReport";
import Home from "./src/components/Home";
import StartBeat from "./src/components/BeatTimers/StartBeat";

class AuthLoadingScreen extends React.Component {
  constructor() {
    super();
    this._bootstrapAsync();
  }

  // Fetch the token from storage then navigate to our appropriate place
  _bootstrapAsync = async () => {
    const beatToken = await AsyncStorage.getItem("beatToken");

    // This will switch to the App screen or Auth screen and this loading
    // screen will be unmounted and thrown away.
    this.props.navigation.navigate(beatToken ? "App" : "BeatAuth");
  };

  // Render any loading content that you like here
  render() {
    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <ActivityIndicator />
        <StatusBar barStyle="default" />
      </View>
    );
  }
}

const AppStack = createStackNavigator({
  Home: { screen: Home },
  ExpectationReport: { screen: ExpectationReport },
  IllegalActivityReport: { screen: IllegalActivity },
  LawAndOrderReport: { screen: LawAndOrderReport }
});

const BeatAuthStack = createStackNavigator({
  StartBeat: { screen: StartBeat }
});

export default createAppContainer(
  createSwitchNavigator(
    {
      AuthLoading: AuthLoadingScreen,
      App: AppStack,
      BeatAuth: BeatAuthStack
    },
    {
      initialRouteName: "AuthLoading"
    }
  )
);