from socket import *

def scan(host: str): 
    def checkPort(port):
        try:
            s = socket(AF_INET, SOCK_STREAM)
            s.settimeout(3)
            if s.connect_ex((host, port)) == 0:
                s.close
                return True
        except Exception:
            return False
    
    for p in range(1, 65535):
        if checkPort(p):
            print('port {} is open!'.format(p))

def get_host():
    return input('Enter your host: ')


if __name__ == '__main__':
    host = get_host()
    print('Trying to scan {}'.format(host))
    scan(host)
