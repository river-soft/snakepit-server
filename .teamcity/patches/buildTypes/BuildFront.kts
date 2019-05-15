package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'BuildFront'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("BuildFront")
    name = "Build front"
}))

