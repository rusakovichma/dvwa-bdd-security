FROM vulnerables/web-dvwa

RUN apt-get update && apt-get install -y netcat