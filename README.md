Code to test sending and receiving of multicast datagram packets.


# Usage

You must have a route for the multicast group you want to test.
To add the default one used by this code:

```bash
ip route add 224.0.0.0/4 dev eth0
```

There are bash and java scripts to both send and receive multicast packets.

If you added the route above, then all of the below command should work by default without any arguments.

## bash

### socat receiver
```bash
Usage:

bash/multicast-receiver [-g <multicast-group-ip>] [-p <port>] [-b <bind_ip>] [-h]
```

### socat sender
```bash
Usage:

bash/multicast-sender [-g <multicast-group-ip>] [-p <port>] [-n <network>] [-h]
```

## java

### java receiver
```bash
Usage: 

cd java && java MulticastReceiver [multicast-group-ip] [port]
```

### java sender
```bash
Usage: 

cd java && java MulticastSender [multicast-group-ip] [port]
```

