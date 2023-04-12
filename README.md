
# Ignite

This project was made during [Courier Hacks: Feature Focus 2022](https://courier-hacks-feature-focus.devpost.com/) and received [Send API award](https://courier-hacks-feature-focus.devpost.com/project-gallery).

<img src="https://firebasestorage.googleapis.com/v0/b/ignite-b0c69.appspot.com/o/Picsart_22-10-29_19-52-27-451.jpg?alt=media&token=819b5408-7971-4ce0-9aa8-fedcaa616146"
/>

Tierd of keeping a track of sending notifications to your clients? You've arrived exactly where you need to be.
Ignite helps in setting intelligent 
notification infrastructure so you can give your 
users the experience they deserve.

## Working

Ignite uses the [Courier](https://www.courier.com/) api to provide smart 
notifications to your clients.

## Project Layout 

<img src ="https://firebasestorage.googleapis.com/v0/b/ignite-b0c69.appspot.com/o/Screenshot%202022-10-23%20150320.png?alt=media&token=d9adf854-ff06-4e7d-95ed-9843d172982a" height=550  />


## SetUp guide 

### Step-up guide 

Start by cloning 

```bash
  git clone https://github.com/HubGenixx/Ignite.git
```

- Firebase 

Ignite uses Firebase as Backend Storage and Authentication 

- [FireBase](https://firebase.google.com/docs/android/setup#:~:text=Open%20the%20Firebase%20Assistant%3A%20Tools,your%20Android%20project%20with%20Firebase.)

Enable Sign-in with Email and password and Google Sign-in 

- [Sign-in methods](https://firebase.google.com/docs/auth/android/password-auth)


### Courier 

- For Sending Email and Sms Ignite uses Courier Api 
- To know about Courier go through their [doc](https://www.courier.com/docs/)


### API Reference

#### Post all items

```http
  Post /api/send
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `message` | `string` | **Required**. Your API key |
| `to` | `string` | **Required**. Your API key |
| `content` | `string` | **Required**. Your API key |
| `body` | `string` | **Required**. Your API key |
| `title` | `string` | **Required**. Your API key |
| `routing` | `string` | **Required**. Your API key |
| `methods` | `string` | **Required**. Your API key |
| `channels` | `string` | **Required**. Your API key |

```bash
     "message": {
        "routing": {
            "method": "all",
            "channels": [
              "sms",
              "email"
            ]
          },
      "content": {
        "body": Hi how are you",
        "title": "Api Testing "
      },
      "to": {
        "email": "riteshsonawane1372@gmail.com",
        "phone_number": "+91 55654"
      },
      
    }
```





## Appendix

[Any additional info](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview)


## Contributing
Do you think you can improve our app in any means? You can 
add any kind of value, fix bugs or point out issues. 
Please read [contributing guide](https://github.com/IgnitePluse/Ignite/blob/master/contributing.md) and [code of conduct](https://github.com/IgnitePluse/Ignite/blob/master/Code_of_conduct.md) before proceeding.

## License
Ignite comes with <a href= "https://github.com/IgnitePluse/Ignite/blob/master/LICENSE" > MIT LICENSE</a> 


## Release 

## [Download apk](https://github.com/IgnitePluse/Ignite/releases/download/v1/Ignite.apk) 
updated as of 10/21/2022 (For android).


## Support

For support or feedback, email us at :

- riteshsonawane1372@gmail.com
- adityasimant88@gmail.com


