from socket import *

def scan(host: str, port: int): 
    try:
        s = socket(AF_INET, SOCK_STREAM)
        s.settimeout(3)
        if s.connect_ex((host, port)) == 0:
            print("port {} is open!".format(port))
            s.close
    except Exception:
        pass


scan("www.google.com", 80)
