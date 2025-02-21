# OnOff

## Author

- Tomas Faal Petersson
- tomas@fpcs.se
- [LinkedIn Profile](https://www.linkedin.com/in/tomasfaalpetersson/)

## Description

The purpose of this application is to retrieve electricity prices from a public API and store them.
Why? To avoid overloading the public (free) API and risk being blocked.

## Configuration

The MongoDB connection string is configured using the environment variable MONGODB_CONNECTION_STRING

## Database

Data is stored in [MongoDB Cloud](https://cloud.mongodb.com/)

See Configuration section above.

The application will create a cluster name "Elpris0".

## Terraform

```
cd infra
gcloud auth application-default login
terraform plan -var="project_id=<numeric project number>"
terraform apply -var="project_id=<numeric project number>"
```

### Code formatting

Use this [template](intellij-java-google-style.xml) in IntelliJ