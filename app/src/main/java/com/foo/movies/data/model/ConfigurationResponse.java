package com.foo.movies.data.model;

import java.util.ArrayList;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public class ConfigurationResponse {

    private Images images;


    public class Images{
        private String baseUrl;

        private String secureBaseUrl;

        private ArrayList<String> backdropSizes = new ArrayList<>();

        private ArrayList<String> logoSizes = new ArrayList<>();

        private ArrayList<String> posterSizes = new ArrayList<>();

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getSecureBaseUrl() {
            return secureBaseUrl;
        }

        public void setSecureBaseUrl(String secureBaseUrl) {
            this.secureBaseUrl = secureBaseUrl;
        }

        public ArrayList<String> getBackdropSizes() {
            return backdropSizes;
        }

        public void setBackdropSizes(ArrayList<String> backdropSizes) {
            if (backdropSizes == null) {
                return;
            }
            this.backdropSizes = backdropSizes;
        }

        public ArrayList<String> getLogoSizes() {
            return logoSizes;
        }

        public void setLogoSizes(ArrayList<String> logoSizes) {
            if (logoSizes == null) {
                return;
            }
            this.logoSizes = logoSizes;
        }

        public ArrayList<String> getPosterSizes() {
            return posterSizes;
        }

        public void setPosterSizes(ArrayList<String> posterSizes) {
            if (posterSizes == null) {
                return;
            }
            this.posterSizes = posterSizes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Images that = (Images) o;

            if (baseUrl != null ? !baseUrl.equals(that.baseUrl) : that.baseUrl != null) return false;
            if (secureBaseUrl != null ? !secureBaseUrl.equals(that.secureBaseUrl) : that.secureBaseUrl != null)
                return false;
            if (backdropSizes != null ? !backdropSizes.equals(that.backdropSizes) : that.backdropSizes != null)
                return false;
            if (logoSizes != null ? !logoSizes.equals(that.logoSizes) : that.logoSizes != null)
                return false;
            return posterSizes != null ? posterSizes.equals(that.posterSizes) : that.posterSizes == null;
        }

        @Override
        public int hashCode() {
            int result = baseUrl != null ? baseUrl.hashCode() : 0;
            result = 31 * result + (secureBaseUrl != null ? secureBaseUrl.hashCode() : 0);
            result = 31 * result + (backdropSizes != null ? backdropSizes.hashCode() : 0);
            result = 31 * result + (logoSizes != null ? logoSizes.hashCode() : 0);
            result = 31 * result + (posterSizes != null ? posterSizes.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return baseUrl;
        }
    }


}
