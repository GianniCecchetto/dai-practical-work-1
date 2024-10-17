[comment]: <> (# dai-practical-work-1) 
# BMP to ASCII/UTF-8 Art CLI - DAI Practical Work 1
### Gianni Cecchetto and Tschantz Nathan

This project is a command-line interface (CLI) tool that 
converts BMP images into grayscale and maps the grayscale 
values to ASCII or UTF-8 characters to create visual art. 
The CLI offers options to customize the output and is built 
using PicoCLI, a framework for command-line applications in Java.

## Installation and Setup
### Prerequisites
Ensure you have the following installed:
* Java 8 or later
* Maven

### Build the Project
1. Clone the repository:
```bash
git clone https://github.com/GianniCecchetto/dai-practical-work-1
cd dai-practical-work-1
```
2. Build the project using Maven:
```bash
./mvnw dependency:resolve clean install package
```
This will download dependencies, compile the code, and create a JAR file in the target/ directory.

## Usage

### Basic Usage
Run the CLI to convert a BMP image into a text-based version (ASCII or UTF-8) with default settings:
```bash
java -jar ./target/DAI-PW1-1.0-SNAPSHOT.jar <inputFilename> <outputFilename> -e <encoding>
```
### Command Options
* ``-h, --help`` : Display the help message.
* ``-V, --version`` : Show version information.
* ``-c, --compression`` : specify a compression factor (integer).
* ``-r, --reverse `` : invert the grayscale (dark <-> light).  
* ``-e, --encoding`` : Specify the text encoding for the conversion, either ``ASCII`` or ``UTF8`` (required).
### Positional Parameters
* ``inputFilename`` : The name of the BMP file you want to convert (required).
* ``outputFilename`` : The name of the file where the ASCII/UTF-8 art will be saved (required).

### Example Commands
1. Convert a BMP file to ASCII art:

```bash
java -jar ./target/ImageToChar-1.0.jar image.bmp output.txt -e ASCII
```
1. Convert a BMP file to ASCII art and reverse the shades:

```bash
java -jar ./target/ImageToChar-1.0.jar image.bmp output.txt -e ASCII -r
```
2. Convert a BMP file to UTF-8 art and compress it by half:
```bash
java -jar ./target/ImageToChar-1.0.jar image.bmp output.txt -e UTF8 -c 2
```
These examples demonstrate how to convert an input BMP 
image into a text file using either ASCII or UTF-8 encoding.