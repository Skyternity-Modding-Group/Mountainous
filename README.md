# Mountainous
The absolute bare-bones (badum psh) skeleton for ReMod Studios' Java Edition mods, based on Architectury.
### Steps to make a new mod out of this template
1. Clone with the Git tool of choice (command-line, IDEA built-in, etc.)
2. Replace *all* occurences of `mountainous`, `Mountainous` and `mountainous` in the project. Detailed list as follows:
    * in the last line of `settings.gradle` (`rootProject.name = "mountainous"`)
    * the `archives_base_name` property in `gradle.properties`
    * the package names for all three modules (`com.cyber2000.mountainous.(fabric|forge)?`)
    * the main class in `common` (`Mountainous`), along with its `MOD_ID` field (`mountainous`)
    * the client class in `common` (`MountainousClient`)
    * the modloader specific entrypoints (`Mountainous(Fabric|Forge)(Client)?`)
    * entrypoints in `fabric.mod.json`
        ```json5
        {
          // ...
          "entrypoints": {
            "main": [{
              "adapter": "kotlin",
              "value": "com.cyber2000.mountainous.fabric.MountainousFabric"
            }],
            "client": [{
              "adapter": "kotlin",
              "value": "com.cyber2000.mountainous.fabric.MountainousFabricClient"
            }]
          },
          // ...
        }
        ```
      * in both `<root>/forge/src/main/resources/META-INF/mods.toml` and `<root>/fabric/src/main/resources/fabric.mod.json`
        ```toml
        [[mods]]
        modId = "mountainous"
        ```
        ```json5
        {
          "schemaVersion": 1,
          "id": "mountainous"
          // ...
        }
        ```
3. Update mod description and dependencies in both `<root>/forge/src/main/resources/META-INF/mods.toml` and `<root>/fabric/src/main/resources/fabric.mod.json`.

## In case if the mod is broken, but you can't quite figure it out
Around 90% of the issues I asked in Architectury's Discord server are related to, if not directly caused by, incorrectly generated run configs.
So before asking, please refresh your run configs by:
   * IDEA: deleting all run configs and re-sync Gradle.
   * Other IDEs: TODO