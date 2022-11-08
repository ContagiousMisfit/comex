package:
	@ mvn clean package

docker-image-build: package
	@ docker build -t alura/comex-api .

run: docker-image-build
	@ docker-compose up -d

stop:
	@ docker-compose down -v

deploy: docker-image-build
	@ docker login --username=_ --password=$$(heroku auth:token) registry.heroku.com
	@ docker image tag alura/comex-api:latest registry.heroku.com/alura-comex-api/web:1
	@ docker image push registry.heroku.com/alura/comex-api/web:1
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/alura-comex-api/web:1 -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
			-H "Authorization: Bearer $$(heroku auth:token)" \
			-H "Content-Type: application/json" \
			-H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
			-d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
			https://api.heroku.com/apps/alura-comex-api/formation
