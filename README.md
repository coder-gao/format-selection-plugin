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

## Configuration

You can customize the keyboard shortcut:

1. Go to `Settings → Keymap`
2. Search for "Format Selection Viewer"
3. Right-click and select "Add Keyboard Shortcut"
4. Enter your preferred shortcut

## Examples

### Formatting JSON
```json
{"name":"John","age":30,"city":"New York"}
```

After formatting:
```json
{
  "name": "John",
  "age": 30,
  "city": "New York"
}
```

### Formatting XML
```xml
<root><item><name>Test</name><value>123</value></item></root>
```

After formatting:
```xml
<root>
  <item>
    <name>Test</name>
    <value>123</value>
  </item>
</root>
```

## Requirements

- IntelliJ IDEA 2022.1 or later
- JDK 11 or later

## Development

### Project Structure

```
src/main/kotlin/com/github/codergao/
├── action/
│   └── FormatSelectionAction.kt          # Main action handler
├── detector/
│   ├── FormatDetector.kt                  # Format detection interface
│   └── DefaultFormatDetector.kt           # Format detection implementation
├── formatter/
│   ├── Formatter.kt                       # Formatter interface
│   ├── JsonFormatter.kt                   # JSON formatter
│   ├── XmlFormatter.kt                    # XML formatter
│   ├── HtmlFormatter.kt                   # HTML formatter
│   ├── SqlFormatter.kt                    # SQL formatter
│   ├── JavaScriptFormatter.kt             # JavaScript/TypeScript formatter
│   ├── CssFormatter.kt                    # CSS formatter
│   └── FormatterFactory.kt                # Formatter factory
├── model/
│   └── TextFormat.kt                      # Text format enum
└── ui/
    └── FormatViewerDialog.kt              # Main UI dialog
```

### Building

```bash
./gradlew build          # Build the plugin
./gradlew runIde         # Run IDEA with the plugin
./gradlew buildPlugin    # Build distribution package
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

MIT License - see LICENSE file for details

## Support

For issues, questions, or suggestions, please open an issue on GitHub.

## Changelog

### v1.0.0 (Initial Release)
- Auto-detection of text format
- Multiple popup windows support
- Format selection dropdown
- Syntax highlighting for formatted text
- Support for major data and programming formats
