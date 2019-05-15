FROM nginx
ADD dist /app
ADD infrastructure/nginx/conf.d /etc/nginx/conf.d/
ADD infrastructure/nginx/nginx.conf /etc/nginx/nginx.conf
ADD infrastructure/nginx/start.sh /etc/nginx/start.sh
RUN /bin/bash -c  "chmod +x /etc/nginx/*.sh"