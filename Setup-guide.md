
# Step-up guide 

Start by cloning 

```bash
  git clone https://github.com/IgnitePluse/Ignite.git
```

- Firebase 

Ignite uses Firebase as Backend Storage and Authentication 

- [FireBase](https://firebase.google.com/docs/android/setup#:~:text=Open%20the%20Firebase%20Assistant%3A%20Tools,your%20Android%20project%20with%20Firebase.)

Enable Sign-in with Email and password and Google Sign-in 

- [Sign-in methods](https://firebase.google.com/docs/auth/android/password-auth)


## Courier 

- For Sending Email and Sms Ignite uses Courier Api 
- To know about Courier go through their [doc](https://www.courier.com/docs/)


## API Reference

#### Get all items

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


## Authors

- [@riteshsonawane1372](https://github.com/riteshsonawane1372)


## Contributing

Contributions are always welcome!

See `contributing.md` for ways to get [started](https://github.com/adityasimant/Ignite/blob/master/contributing.md).

Please adhere to this project's [`code of conduct`](https://github.com/IgnitePluse/Ignite/blob/master/Code_of_conduct.md).


## Demo

Check the latest release [apk](https://github.com/IgnitePluse/Ignite/releases/download/v1/Ignite.apk)


## Support

For support, email 

- riteshsonawane1372@gmail.com
- adityasimant88@gmail.com


## Feedback

If you have any feedback, please reach out to us at riteshsonawane1372@gmail.com


![Logo](https://firebasestorage.googleapis.com/v0/b/ignite-b0c69.appspot.com/o/Repo%20Images%2Fignite%20(1).png?alt=media&token=826db3d8-7a99-40d8-bf27-280dc1c36abf)

