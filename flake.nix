{
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  inputs.flake-parts.url = "github:hercules-ci/flake-parts";
  inputs.gradle2nix.url = "github:tadfisher/gradle2nix/v2";
  inputs.gradle2nix.inputs.nixpkgs.follows = "nixpkgs";

  outputs =
    inputs:
    inputs.flake-parts.lib.mkFlake { inherit inputs; } (
      { self, ... }:
      {
        systems = [
          "aarch64-darwin"
          "aarch64-linux"
          "x86_64-darwin"
          "x86_64-linux"
        ];
        perSystem =
          {
            self',
            inputs',
            pkgs,
            ...
          }:
          {
            packages.gradle2nix = inputs'.gradle2nix.packages.default;
            packages.HyCord = pkgs.callPackage (
              {
                hytaleServerJar ? (
                  builtins.addErrorContext ''
                    Please build this package with
                    $ HYTALE_SERVER_JAR=/path/to/HytaleServer.jar nix build .#HyCord --impure
                    or by using:
                    HyCord.override { hytaleServerJar = pkgs.fetchurl { url = "https://invalid/url/to/HytaleServer.jar"; hash = ""; }; }
                  '' (builtins.path { path = builtins.getEnv "HYTALE_SERVER_JAR"; })
                ),
              }:
              self'.packages.gradle2nix.passthru.buildGradlePackage {
                pname = "HyCord";
                version = "1.0.0";
                lockFile = ./gradle.lock;
                gradleBuildFlags = [ "build" ];
                src = ./.;
                preBuild = ''
                  mkdir -p ./libs
                  cp ${hytaleServerJar} ./libs/HytaleServer.jar
                '';

                installPhase = ''
                  runHook preInstall
                  mkdir -p $out/libs
                  cp -r build/libs $out/libs
                  runHook postInstall
                '';
              }
            ) { };
            packages.default = self'.packages.HyCord;
          };
      }
    );
}
