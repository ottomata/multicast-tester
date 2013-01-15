#!/bin/bash

test -z $(which socat) && (echo "socat must be installed" && exit 1)
progname=$(basename $0)

function get_ip {
    test -n $(which facter) && ip=$(facter ipaddress)
    echo $ip
}

# defaults
group_ip="224.1.0.1"
port="14454"
network="$(get_ip)/32"
bind_ip="$(get_ip)"