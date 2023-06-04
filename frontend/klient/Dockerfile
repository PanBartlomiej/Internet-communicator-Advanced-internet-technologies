# Używamy obrazu bazowego z Node.js
FROM node

# Ustalamy katalog roboczy w kontenerze
WORKDIR /app

# Kopiujemy pliki aplikacji do kontenera
COPY . .

# Instalujemy zależności
RUN npm install

# Budujemy aplikację
RUN npm run build

# Określamy etap docelowy, który będzie serwował aplikację
FROM nginx

# Kopiujemy skompilowane pliki aplikacji do kontenera NGINX
COPY --from=0 /app/build /usr/share/nginx/html

# Określamy port, na którym działa aplikacja
EXPOSE 3000

# Uruchamiamy serwer NGINX
CMD ["nginx", "-g", "daemon off;"]
