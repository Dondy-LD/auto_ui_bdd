#Feature: UVMS Live View
#
  #Background: User generates token for authorisation
    #Given I am an authorized user
#
  #Scenario Outline: user able to execute the search resource
    #Then search channel with "<channelName>" and "<sharedListType>"
#
    #Examples: 
      #| channelName       | sharedListType   |
      #| QE_HIK_TEST_11-72 | channel.liveview |
#
  #Scenario Outline: live view
    #Then search channel with "<channelName>" and "<sharedListType>"
    #Then select channel to live view
    #Then live view should be ok
#
    #Examples: 
      #| channelName       | sharedListType   |
      #| QE_HIK_TEST_11-72 | channel.liveview |
#
  #Scenario Outline: live view end
    #Then search channel with "<channelName>" and "<sharedListType>"
    #Then select channel to live view
    #Then live view should be ok
    #Then close the live view
#
    #Examples: 
      #| channelName       | sharedListType   |
      #| QE_HIK_TEST_11-72 | channel.liveview |
