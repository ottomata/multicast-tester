Code to test sending and receiving of multicast datagram packets.

You must have a route for the multicast group you want to test.
To add the default one used by this code:

```bash
ip route add 224.0.0.0/4 dev eth0
```
