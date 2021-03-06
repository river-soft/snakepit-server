package patches.projects

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    check(archived == false) {
        "Unexpected archived: '$archived'"
    }
    archived = true

    features {
        add {
            feature {
                type = "OAuthProvider"
                id = "PROJECT_EXT_10"
                param("secure:aws.secret.access.key", "credentialsJSON:b7324aeb-a7e4-4f7e-983a-33ace2802ebb")
                param("displayName", "Amazon ECR")
                param("aws.access.key.id", "AKIA3UFSQWK7RAXX4MN6")
                param("aws.credentials.type", "aws.access.keys")
                param("aws.region.name", "eu-central-1")
                param("registryId", "799238304447")
                param("providerType", "AmazonDocker")
            }
        }
    }
}
