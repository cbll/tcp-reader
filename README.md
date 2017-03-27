# tcp-reader

A small Java program that pipes to tcpdump and for now measures incoming and outgoing traffic from the local machine.

Find the given network interface with ifconfig, then pipe the program as such: sudo tcpdump -nl –i <interface> | java tcpReader
