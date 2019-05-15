echo "## START NGINX"

export DOLLAR="$"
envsubst < /etc/nginx/conf.d/template.nginx > /etc/nginx/conf.d/default.conf
exec nginx -g "daemon off;"