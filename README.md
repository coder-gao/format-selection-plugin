# Format Selection Plugin for IntelliJ IDEA

A powerful plugin that automatically detects and formats selected text in IntelliJ IDEA without modifying the original file.

## Features

✨ **Auto-Detection**: Automatically detects text format (JSON, XML, HTML, SQL, JavaScript, CSS, YAML, etc.)

📋 **Non-Destructive**: View formatted text in a separate popup window without changing the original file

🔄 **Multiple Windows**: Each selection opens a new independent popup window

📌 **Pin & Close**: Windows can be pinned to the screen or easily closed

🎯 **Format Selector**: Dropdown menu to manually select or change the format

⌨️ **Quick Access**: Use `Ctrl+Alt+Shift+F` (Windows/Linux) to open the formatter

## Supported Formats

- **Data Formats**: JSON, XML, HTML, YAML
- **Programming Languages**: JavaScript, TypeScript, SQL, CSS, Python, Java, Kotlin
- **Configuration**: Properties files
- **Plain Text**: Fallback for unrecognized formats

## Installation

### From Source

1. Clone the repository:
```bash
git clone https://github.com/coder-gao/format-selection-plugin.git
cd format-selection-plugin
```

2. Build the plugin:
```bash
./gradlew build
```

3. The plugin JAR will be in `build/libs/format-selection-plugin-1.0.0.jar`

4. Install in IDEA:
   - Go to `Settings → Plugins → Install Plugin from Disk`
   - Select the JAR file
   - Restart IDEA

## Usage

1. **Select Text**: Highlight any text in your editor
2. **Open Formatter**: Press `Ctrl+Alt+Shift+F` or go to `Edit → Format Selection Viewer`
3. **View Formatted**: The formatted version appears in a new popup window
4. **Change Format**: Use the dropdown at the top to manually select a different format
5. **Close**: Click the X button to close the popup (or keep it pinned)

## Keyboard Shortcuts

- **Windows/Linux**: `Ctrl+Alt+Shift+F`
- **Mac**: `Cmd+Option+Shift+F` (configurable)

## Development

### Requirements

- IntelliJ IDEA 2023.1 or later
- JDK 11 or later
- Gradle 7.0+

### Building

```bash
./gradlew build          # Build the plugin
./gradlew runIde         # Run IDEA with the plugin
./gradlew buildPlugin    # Build distribution package
```

## License

MIT License - see LICENSE file for details
