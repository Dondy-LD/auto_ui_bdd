#Feature: UVMS Playback
#
  #Background: User generates token for authorisation
    #Given I am an authorized user
#
  #Scenario Outline: user able to execute the search resource
    #Then search channel with "<channelName>" and "<sharedListType>"
    #Then search recorded video of the channel with "<fromTime>" and "<toTime>"
    #Then playback should be ok
#
    #Examples: 
      #| channelName | sharedListType   | fromTime      | toTime        |
      #| HIK 11-6352 | channel.playback | 1594603200000 | 1594603800000 |
