# GCMTest

This is GCM Simple Demo in AndroidStudio.

## Usage

git clone git@github.com:oggata/GCMTest.git

- 0)dev console
https://console.developers.google.com/
- 1)Cloud Messaging for Android -> Set Effective
- 2)Create Project ID and Number
- 3)Create API Key.

#### Test POST Command
```bash
curl --header "Authorization: key=hoge1" --header Content-Type:"application/json" https://android.googleapis.com/gcm/send -d "{\"registration_ids\":[\"hoge2\"],\"data\":{\"message\":\"Hello\"}}"
```

## ToDo's

- send message by toast. 

## Document

## Contact

Fumitoshi Ogata
- [twitter.com/oggata](http://twitter.com/oggata)
- oggata@gmail.com

## License
FacialRecognition is available under the MIT license. 
See the LICENSE file for more info.