provider "google" {
  project = var.project_id
  region  = var.region
}

# Define variables
variable "project_id" {}
variable "region" {
  default = "europe-north1"
}
variable "service_name" {
  default = "onoff-updater"
}

# Enable Cloud Scheduler
resource "google_project_service" "cloud_scheduler" {
  project = var.project_id
  service = "cloudscheduler.googleapis.com"
}

# Grant IAM Role to Cloud Build
resource "google_project_iam_member" "cloud_build_scheduler_admin" {
  project = var.project_id
  role    = "roles/cloudscheduler.admin"
  member  = "serviceAccount:${var.project_id}@appspot.gserviceaccount.com"

  depends_on = [google_project_service.cloud_scheduler]
}

# Create a Service Account
resource "google_service_account" "onoff_updater_sa" {
  account_id   = "onoff-updater-sa"
  display_name = "Service Account for onoff-updater"
}

# Assign the Minimal IAM Role (Cloud Run Invoker)
resource "google_project_iam_member" "cloud_run_invoker" {
  project = var.project_id
  role    = "roles/run.invoker"
  member  = "serviceAccount:${google_service_account.onoff_updater_sa.email}"
}

# Assign Logging Role for Debugging
resource "google_project_iam_member" "logging_writer" {
  project = var.project_id
  role    = "roles/logging.logWriter"
  member  = "serviceAccount:${google_service_account.onoff_updater_sa.email}"
}
# ✅ Allow Cloud Build to act as this service account
resource "google_service_account_iam_member" "cloud_build_can_use_sa" {
  service_account_id = google_service_account.onoff_updater_sa.name
  role               = "roles/iam.serviceAccountUser"
  member             = "serviceAccount:${var.project_id}@cloudbuild.gserviceaccount.com"
}
